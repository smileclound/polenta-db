# Polenta - Java in-memory database 

<p><i>A personal project to, along with</i> <a href="https://github.com/pmbr/polentadriver">Polenta Driver</a>, <a href="https://github.com/pmbr/polentashell">Polenta Shell</a> <i>and</i> <a href="https://github.com/pmbr/polentaclient">Polenta Client</a><i>, improve Java and algorithms skills.</i>

Bags
====

A bag is a set of rows without primary key. It's not possible to delete data from a bag, only insert to and select from.  

<pre>
CREATE BAG [BAG_NAME] ([FIELD_NAME FIELD_TYPE], [FIELD_NAME FIELD_TYPE], ...) 

   Supported field types: STRING, INTEGER, DATE (YYYY-MM-DD), DOUBLE

INSERT INTO [BAG_NAME] ( [FIELD_NAME], [FIELD_NAME, ...) VALUES ([VALUE, [VALUE, ...)

SELECT [FIELD_NAME], [FIELD_NAME], ... FROM [BAG_NAME] WHERE [FIELD_NAME = VALUE] ORDER BY [FIELD_NAME] 

   Field names must be defined on select, "*" is not supported. WHERE and ORDER BY supports only one field.

DROP [BAG_NAME]
</pre>