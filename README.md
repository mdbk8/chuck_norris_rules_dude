# README #

This README would normally document whatever steps are necessary to get your application up and running.

### What is this repository for? ###

* Quick summary
* Version
* [Learn Markdown](https://bitbucket.org/tutorials/markdowndemo)


## How to run the application

To run application you can use saved run configs in `run-configs` directory.
It needs Kafka to be up and running. To do so invoke `docker compose -f producer-compose.yaml up -d`.
It will run Zookeeper, Kafka broker and Portainer in the background.

To stop these containers run `docker compose -f producer-compose.yaml stop`.

To stop and remove these containers run `docker compose -f producer-compose.yaml down`.

### Portainer

In `dependencies-dev.yaml` file there is Portainer defined which helps to manage running containers on local machine.
You can access it by navigating to [localhost](http://localhost:9000).

### Other useful commands

- `docker compose -f producer-compose.yaml logs -f zookeeper` - to see zookeeper logs
- `docker compose -f producer-compose.yaml logs -f kafka` - to see kafka logs