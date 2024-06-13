workspace {

    model {
        issuer = person "Issuer (Docent)"
        holder = person "Holder (Student)"
        ebsi = softwareSystem "EBSI" "" "EBSI"

        ss = softwareSystem "EPOS System"{
            web = container "Education Web Portal" "" "" "HU" {
                issuer -> this "Uses"
            }
            web-backend = container "Education Web Portal API" "" "" "HU"{
                web -> this "Reads from and writes to"
            }
            database = container "Database" "" "" "Database" {
                web-backend -> this "Reads from and writes to" 
            }
            keycloak = container "Keycloak" "" "" "" {
                web-backend -> this "Uses"
            }

            issuer-service = container "Issuer Service" "" "" "Walt.id" {
                web-backend -> this "Reads from and writes to"
                this -> ebsi "Uses"
            }
            vault = container "Vault" "" "" "" {
                issuer-service -> this "Uses"
            }
            wallet = container "Wallet" "" "" "Walt.id" {
                holder -> this "Uses"
                this -> ebsi "Uses"
            }
        }
    }

    views {
        systemContext ss "Context-diagram" {
            include *
        }

        container ss "Container-diagram" {
            include *
        }
        styles {
            element "Person" {
                shape person
                background #1D0D34 
                color #ffffff
            }
            element "Database" {
                shape cylinder
                background #f4f4f4
            }

            element "Software System" {
                shape roundedBox
                background #00b3ef
                color #ffffff
            }
            element "EBSI" {
                shape roundedBox
                background #C91C88
                color #ffffff
            }
            element "HU"{
                shape roundedBox
                background #04a6dd
                color #000000
            }
            element "Walt.id"{
                shape roundedBox
                background #0b83ab
                color #000000
            }


        }
    }

    configuration {
        scope softwaresystem
    }
    

}