Stack:
- Java 1.8
- Maven 3
- Selenium 3.14.0
- TestNG 6.14.3

SO: Windows 10.

Navegador: Chrome Versión 84.0.4147.125 (Build oficial) (64 bits)

Ejecución:
    Mediante IDE (Eclipse o IntelliJ IDEA), ejecución con TestNG driver.
    Maven: mvn test -DsuiteXmlFile={ruta al archivo de configuracion} ( src/test/resources/suites/serviceTest.xml o src/test/resources/suites/busquedaHeladeraTest.xml )

Aclaraciones:
Se agrega parametrización para seleccion de categoria y/o subcategoria con el metodo acceder categoria, el cual aplica Method Overloading.

Casos de prueba: https://docs.google.com/spreadsheets/d/1sNaJilc9xt0JTGlcjbJfxPMZ6zjYSuvLYH9kFm41liY/edit?usp=sharing


