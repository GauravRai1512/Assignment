MySQL database used. Have installed mysql database server on my system and then created table review to store product review details.

Review Service:
1. In Review Service, created mysql connection and with crud operations created, updated , deleted and retrieved a per instructions.
2. Exception Handling is there as per use case.
3. Resiliency is also there through retry.
4. Have created docker file as well, docker engine was creating problem on my system so was not able to create the image but command is already in docker file.
5. Basic Authentication is implemented, GET is accessible without authentication and for rest crud you have to provide username & password.

Product Service:
1. Get operation is there to retrieve aggregated details.
2. Tried to call adidas api but it was giving socket timeout.
3. called get product review service to aggregate the response on port 8080.
4. product service is running on port 8082