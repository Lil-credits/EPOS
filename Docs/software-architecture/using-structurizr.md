# Structurizr

The architecture diagrams are based on the [C4-model](https://c4model.com/) and implemented using [Structurizr](https://structurizr.com/), a diagrams as code tool set. To see the diagrams for this project see the [main page](./epos-software-architecture.md)

## Getting started

To view the architecture diagrams in the structurizr tool use the following commands.

Pull structurizr lite from docker:
```
docker pull structurizr/lite
```
Then spin up structurizr by running:
```
docker run -it --rm -p 8080:8080 -v [PATH_TO_REPOSITORY]/Docs/software-architecture:/usr/local/structurizr structurizr/lite
```
Where [PATH_TO_REPOSITORY] is replaced with the location of the repository. Make sure to include "C:".

If structurizr has started successfully navigate to http://localhost:8080 to view the diagrams.

To make changes to the diagrams edit the workspace.dsl and refresh the website.

## Editing diagrams

To get syntax highlighting in the workspace.dsl file install the `ciarant.vscode-structurizr` vscode extension. More information about the syntax can be found on the [structurizr documentation](https://structurizr.com/help) and in the [cookbook](https://github.com/structurizr/dsl/tree/master/docs/cookbook).
