<?xml version="1.0" encoding="UTF-8"?>
<arquillian xmlns="http://jboss.org/schema/arquillian"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://jboss.org/schema/arquillian http://jboss.org/schema/arquillian/arquillian_1_0.xsd">
    <container qualifier="glassfish-embedded" default="true">
        <configuration>
            <property name="bindHttpPort">7171</property>
            <#--<property name="resourcesXml">
                src/test/resources/glassfish-resources.xml
            </property>-->
        </configuration>
    </container>
</arquillian>