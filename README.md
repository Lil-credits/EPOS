<div align="center">
<h1>EPOS</h1>
<small>European Proof of Skill</small>
<hr>
</div>

### Background
A project from the HU University of Applied Sciences Utrecht. Build by students of [Open-ICT](https://www.hu.nl/voltijd-opleidingen/open-ict). <br>
We did use [walt.id](https://github.com/walt-id/waltid-identity) as base for the project and build new things on top of it.

## Getting started
Before getting started make sure you have installed [docker](https://docs.docker.com/get-docker/)

clone this repository
```shell
git clone https://github.com/Lil-credits/EPOS.git
```

Start the docker with the following commands
```shell
docker-compose build && docker-compose up
```

The docker app should now be running and you can start using the app.
### Folders

`docs` <br>
Documentation about the project for transferring the project to another team in the feature.

`Waltid-wallet` <br>
The waltid webwallet, this folder contains an API and a frontend. to let the wallet fully function.

`Waltid-api` <br>
The waltid API services for issuing and verifying. We use this API in our backend

`EPOS` <br>
The 