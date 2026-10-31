# Java Distributed Computing Project

A two-phase Java project exploring client/server communication with TCP sockets, threads, and Remote Method Invocation (RMI).

## Phase 1: Countdown with TCP sockets

A client sends a starting integer to a server, which returns the countdown from that number to 1.

- **Iterative version:** handles one client connection at a time on port `5000`.
- **Concurrent version:** starts a separate thread for each client connection on port `5001`.
- Both clients prompt for a hostname, port, and starting number. Press Enter at the hostname and port prompts to use the defaults.

This phase compares sequential request handling with a thread-per-client approach.

## Phase 2: Election with Java RMI

A console client calls methods on a remote election server. Users can vote for Alice or Bob, view the current totals, or exit.

- `Election.java` defines the remote interface: `castVote` and `getResult`.
- `ElectionServer.java` implements the interface and stores the vote counts.
- `ElectionClient.java` provides the interactive menu.

The server starts its own RMI registry on port `1099` and registers `ElectionService`. The client is configured to connect to `localhost`.

## Project structure

```text
java-distributed-computing-project/
|-- README.md
|-- .gitignore
|-- phase-1-countdown/
|   `-- src/
|       |-- IterativeCountdownClient.java
|       |-- IterativeCountdownServer.java
|       |-- ConcurrentCountdownClient.java
|       `-- ConcurrentCountdownServer.java
`-- phase-2-election/
    `-- src/
        |-- Election.java
        |-- ElectionClient.java
        `-- ElectionServer.java
```

## Requirements

Install a Java Development Kit (JDK) with `java` and `javac` available in your terminal. No external libraries or build tools are required. The sources were checked with OpenJDK 24.

## Compile

Run these commands from the repository root:

```sh
javac -d phase-1-countdown/out phase-1-countdown/src/IterativeCountdownClient.java phase-1-countdown/src/IterativeCountdownServer.java phase-1-countdown/src/ConcurrentCountdownClient.java phase-1-countdown/src/ConcurrentCountdownServer.java
javac -d phase-2-election/out phase-2-election/src/Election.java phase-2-election/src/ElectionClient.java phase-2-election/src/ElectionServer.java
```

## Run Phase 1

Start the iterative server:

```sh
java -cp phase-1-countdown/out IterativeCountdownServer
```

In a second terminal, opened at the repository root:

```sh
java -cp phase-1-countdown/out IterativeCountdownClient
```

For the concurrent version, start its server:

```sh
java -cp phase-1-countdown/out ConcurrentCountdownServer
```

Then run its client in another terminal:

```sh
java -cp phase-1-countdown/out ConcurrentCountdownClient
```

You can open additional client terminals to try multiple connections. For example, entering `5` as the starting number returns `5, 4, 3, 2, 1`.

## Run Phase 2

Start the election server:

```sh
java -cp phase-2-election/out ElectionServer
```

In a second terminal, opened at the repository root:

```sh
java -cp phase-2-election/out ElectionClient
```

The menu offers:

```text
1. Vote for Alice
2. Vote for Bob
3. View Results
4. Exit
```

Keep the corresponding server running while using a client. Stop a server with Ctrl+C. A separate `rmiregistry` command is not needed.

## Learning outcomes

- Sending and receiving integers over TCP sockets.
- Comparing iterative servers with concurrent servers.
- Handling client connections with Java threads.
- Defining and implementing remote interfaces with Java RMI.
- Separating client interaction from server-side processing.

## Scope and limitations

This project is an educational demonstration of distributed computing.

- Votes are held in memory and reset when the election server restarts.
- The election demo has no voter authentication or restriction on repeat votes.
- Vote increments are not synchronized, so simultaneous voting can produce incorrect totals.
- Input validation and error handling are basic; nonnumeric menu input can end a client session.
- The concurrent countdown server creates one thread per connection without a thread limit.

IDE configuration and compiled classes are excluded from this repository.

