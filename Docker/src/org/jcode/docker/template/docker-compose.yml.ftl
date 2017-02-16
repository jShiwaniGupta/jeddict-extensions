version: '2'
services:
    web:
        build:
            context: .
            dockerfile: DockerFile
            args:
                BINARY: maven/${r"${build_name}"}.war
                DB_DATASOURCE: '${docker.dataSource}'
                DB_NAME: '${docker.dbName}'
                DB_USER: '${docker.dbUserName}'
                DB_PASS: '${docker.dbPassword}'
                DB_HOST: 'db'
<#if docker.databaseType == "MySQL" || docker.databaseType == "MariaDB">
                DB_PORT: '3306'
<#elseif docker.databaseType == "PostgreSQL">
                DB_PORT: '5432'
</#if>
        ports:
            - "8080:8080" 
            - "8081:8081"
        links:
            - db 
    db:
<#if docker.databaseType == "MySQL" || docker.databaseType == "MariaDB">
        <#if docker.databaseType == "MySQL">
        image: mysql:${docker.databaseVersion}
        <#elseif docker.databaseType == "MariaDB">
        image: mariadb:${docker.databaseVersion}
        </#if>
        ports:
            - "3306:3306"  
        environment:
            MYSQL_ROOT_PASSWORD: '${docker.dbPassword}'
            MYSQL_USER: '${docker.dbUserName}'
            MYSQL_PASSWORD: '${docker.dbPassword}'
            MYSQL_DATABASE: '${docker.dbName}'
#        volumes:
#            - data-mysql:/var/lib/mysql
#volumes:  
#    data-mysql:
#        driver: local       
<#elseif docker.databaseType == "PostgreSQL">
        image: postgres:${docker.databaseVersion}
        ports:
            - "5432:5432"  
        environment:
            POSTGRES_USER: '${docker.dbUserName}'
            POSTGRES_PASSWORD: '${docker.dbPassword}'
            POSTGRES_DB: '${docker.dbName}'
#        volumes:
#           - data-postgres:/var/lib/postgresql/data
#volumes:  
#    data-postgres:
#      driver: local
</#if>