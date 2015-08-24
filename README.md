# clj-web-example

A sample project with browser-based user interface written using Clojure,
ClojureScript and MongoDB. Uses Vagrant for development environment and
Docker for production deployment.

The app itself is ugly and does nothing interesting. However, the code is meant
to be a starting point or a pony for developing new apps. The Git log is even
more important than the code itself!

Why not develop a Leiningen template? Because the code contains example
business logic that should not be copied to new projects. Also, Vagrant and
Docker configurations should probably not be packaged into Leiningen templates.

## Usage

1. Install Git
2. Install VirtualBox
3. Install Vagrant
4. Install Leiningen
5. Clone the repository
6. Explore the code by using the following commands in the cloned directory.

```bash
# Download, install and run a virtualization environment for MongoDB.
# Takes a while... make sure that Intel Virtualization techonology VT-d is on.
vagrant up

# In development, the above command starts MongoDB inside a Docker container
# running inside a proxy virtual machine. The application should be run at
# the host machine by running:
cd app
lein repl
(go)

# You can reset the backend (server, routes, etc) while in REPL
(reset)

# Keep figwheel and less4j running during development (in separate terminal).
lein develop

# The above command calls ClojureScript compiler, starts Figwheel and the
# application itself.
# If you need an uberjar, then run:
lein build

# Tests are run normally.
lein test
```

## Using this example as a starting point

1. Fork the repository.
2. Install Counterclockwise.
3. Use file search to replace clj-web-example with [your-project-name] in the
whole project.
4. Use Refactor -> Rename to clj-web-example namespace. Tick
"Rename subpackages".
5. Use Counterclockwise or some other tool to replace clj-web-example to
[your-project-name].
6. Review project.clj.
7. Start editing the app itself (routes, api functions, reagent views,
  localizations, Dockerfile, etc.).

## Installation to production (uses Docker, so Linux-servers only)

1. Install Git
2. Install Docker
3. Install Leiningen (and Java)
3. As a user that can sudo:

```bash
git clone https://github.com/MattiNieminen/weather.git
cd clj-web-example
chmod a+x os/dockerize.sh
./os/dockerize.sh
```

Let me know if you need instructions for production Windows-servers!

## License

Copyright © 2015 Matti Nieminen

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
