
*This project is backend startcode for projects in 3rd semester*

### Preconditions
*To use this code you should have a local developer setup with a matching setup on a virtual server, this setup is described in the following documents*
- [Developer setup on local machine](https://docs.google.com/document/d/1aBUJrZkWGmyqgnZ7GncMEQYJdX3euuFi_j-N0D63QyM)
- [Virtual-server setup](https://docs.google.com/document/d/1tY1QKk4CK70iH0abeetCDMgNhKFhR558V9J4_0at-9I)


### Setup database
- Connect to your database on your local docker
- Create new database (Remember name for later use)
- Use the provided SQLDump (located in project root folder) the populate database with an admin and user
- Repeat with your virtual machine

### Setup in Intellij
- Open view->too windows->persistence
- Open the Database tab and create a new data source (remember to point to a database event though this is already written in the persistence unit. This is necessary in order to use the JPQL console)
- Open pom.xml
  - Change <name>dat3-startcode</name> to something that soothes you
  - Change <remote.server></remote.server> to your own domain name ie. https://XXX.dk/manager/text
  - Change <db.name></db.name> to the name of the database created in section "Setup database" on your virtual-machine


### Github setup
- Create a new repository on github
- Open repository settings -> Secrets
- Click "New repository secret"
  - Name it REMOTE_USER
  - In value type tomcat username to the server added in pom.xml file
- Click "New repository secret"
  - Name it REMOTE_PSW
  - In value type tomcat password to the server added in pom.xml file



