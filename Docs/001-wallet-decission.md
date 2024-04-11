<h1 align="center">Wallet decission</h1>

## Status

Status: **proposed**

## Context

We did research about organisation and personal wallets which wallet is most suitable for the micro credential project.

We looked at all wallet providers which are opensource and EBSI 3 compliant

### Personal wallets

|                   | Active maintained | Development team size | Community size, github stars | Usability |      sustainability  |
|-------------------|-------------------|-----------------------|------------------------------|-----------|----------------|
|      Walt.id      | +-                | +                     | ++                           | +         |     ++         |
|      Talao/Altme  | +-                | -                     | +                            | ++        |     ?          |
|      Masca        | -                 | +-                    | +                            | --        |     ?          |
|      IN2 Wallet   | ++                | +                     | --                           | --        |     +          |

### Organisation wallets
There is no organisation wallet which is EBSI 3 compliant and opensource, so we had to make the decision to look for other options. 
During the research we found walt.id is going to upgrade to EBSI 3 by the end of March. Walt.id also do have libraries for developing your own wallet.
We took this is consideration as well.

Beside walt.id we also did some research about creating our own wallet from scratch.

## Decision

### Personal wallet (holder wallet)
Our decision for the personal is wallet is using the (web)wallet from walt.id as pilot wallet. It should be possible to use any EBSI compliant wallet.

### Organisation wallet (issue, verify)
Our decission for the organisation wallet is using the already build wallet from walt.id. Later on it's also possible to add functionalities to this wallet.

## Consequences

There are currently no consequences because we didn't have any codebase yet. Later on it should be possible to develop a POC in a short amount of time.

## Update (April 2024)

After building Proof of Concepts for both walt.id and paradym.io as wallet providers, we have decided to continue with walt.id for our project. Although Paradym.io did not fit our criteria for selecting a wallet which was EBSI compliance, the development experience with the paradym.id platform and communication with their team, Animo Solutions, was pleasant. We see potential for collaboration on other Self-Sovereign Identity (SSI) projects in the future.
