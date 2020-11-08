# Converter Application

## Summary
Simple console-based application which can take and return JSON file with objects in such formats:
* Input
<pre>
{
  "distance" : {
                 "unit" : "cm", 
                 "value" : 0.5
                },
  "convert_to" : "ft"
}
</pre>
* Output
<pre>
{
  "value" : 0.02,
  "unit" : "ft"
}
</pre>
## Technologies
* Jackson
