# README #

This README would normally document whatever steps are necessary to get your application up and running.

### What is this repository for? ###

* Quick summary
* Version
* [Learn Markdown](https://bitbucket.org/tutorials/markdowndemo)


## How to run the application

To run application you can use saved run configs in `run-configs` directory.
It needs Kafka to be up and running. To do so invoke `docker compose -f chuck-producer-dependencies-compose.yaml up -d`.
It will run Zookeeper, Kafka broker and Portainer in the background.

To stop these containers run `docker compose -f chuck-producer-dependencies-compose.yaml stop`.

To stop and remove these containers run `docker compose -f chuck-producer-dependencies-compose.yaml down`.

It's also possible to run the application together with Zookeeper and Kafka in Docker.
To do so run `chuck-producer-compose.yaml` file the same way as described above.

### Portainer

In `dependencies-dev.yaml` file there is Portainer defined which helps to manage running containers on local machine.
You can access it by navigating to [localhost](http://localhost:9000).

### Other useful commands

- `docker compose -f chuck-producer-dependencies-compose.yaml logs -f zookeeper` - to see zookeeper logs
- `docker compose -f chuck-producer-dependencies-compose.yaml logs -f kafka` - to see kafka logs

### Remote debug

When running application inside Docker, you can attach remote debugger by running `Docker_debug` run config from 
`run-configs` dir.

## Mutation testing

[Pitest](https://pitest.org/) is configured in each module.
You can run it by invoking appropriate run config stored in `run-configs` dir.
It runs mutation for all classes.

To test only newly added/modified files you have to change `pitest` plugin config:
- comment out existing list entry for whole project package
- add one or multiple classes entry in the form of `"*.ChuckController"`

After such changes `./gradlew pitest` command will mutate only specified classes but run all tests.

To also narrow down tests that will be run, do the same changes but for `targetClasses` entry in `pitest` plugin 
config.