package demo
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

import java.text.DateFormatSymbols
import java.text.SimpleDateFormat

import org.codehaus.groovy.runtime.NullObject

def jsonSlurper = new JsonSlurper()
def object = jsonSlurper.parseText '''
{
   "fileId":"00001",
   "filter":{
      "startDate":"08/05/2015",
      "endDate":"08/10/2015"
   },
   "data":[
      {
         "logicField":{
            "extraInfoKey":{
            "B":"Buy",
            "S":"Sell"
             },
            "isMandatory":true,
            "logicName":"Buy/Sell"
         },
         "$$hashKey":"object:5",
         "column":"4",
         "enum_map":{
            "B":"Buy",
            "S":"Sell"
         }
      },
      {
         "logicField":{
            "extraInfoKey":"format",
            "isMandatory":true,
            "logicName":"Contract Date"
         },
         "$$hashKey":"object:6",
         "column":"10",
         "format":{
            "dd/MM/yyyy":"Format",
            "S":"Sell"
         }
      },
      {
         "logicField":{
            "extraInfoKey":null,
            "isMandatory":true,
            "logicName":"Net Amount"
         },
         "$$hashKey":"object:7",
         "column":"9"
      },
      {
         "logicField":{
            "extraInfoKey":null,
            "isMandatory":true,
            "logicName":"Security Code"
         },
         "$$hashKey":"object:8",
         "column":"5"
      },
      {
         "logicField":{
            "extraInfoKey":null,
            "isMandatory":true,
            "logicName":"Units"
         },
         "$$hashKey":"object:9",
         "column":"6"
      }
   ]
}
'''
object.data.each { d ->
    println d.column
    println d.logicField.logicName
    println d.logicField.extraInfoKey
    println d.logicField.extraInfoKey.getClass().getName()
    println d.logicField.extraInfoKey instanceof NullObject
    println d.logicField.extraInfoKey != null
    println d.logicField.extraInfoKey.equals(null)
}

def map = [4: 'a', 5: 'c']
println map[4]
println map.getClass()

println new SimpleDateFormat('dd/MM/yyyy').parse('20/01/2015').toString()


def sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
sdf.setTimeZone(TimeZone.getTimeZone("Australia/Sydney"))
sdf.setDateFormatSymbols(DateFormatSymbols.getInstance(Locale.ENGLISH))
println sdf.format(new Date())

def testList = ['B', 'B', 'S', 'S', 'Z']
def keySet = []
testList.groupBy {it}.keySet().each {
    keySet << it
}
keySet << null
println keySet
println testList.countBy { it }.groupBy { it.value }.max { it.key }.value
testList.countBy { it }.groupBy { it.value }.max { it.key }.value.each { key, value->
    println "${key} : ${value}"
}


println new SimpleDateFormat('d/MM/yyyy').parse('11/01/2015').toString()
println new SimpleDateFormat('dd/MM/yyyy').parse('5/01/2015').toString()
println new SimpleDateFormat('d/M/yyyy').parse('05/01/2015').toString()
println new SimpleDateFormat('dd/MM/yy').parse('5/08/2011').toString()
println new SimpleDateFormat('dd/MM/yy').parse('5/08/11').toString()
println new SimpleDateFormat('yy/MM/dd').parse('2014/08/11').toString()

Set set = new HashSet()
set.add('a')
set.add('b')
def json = new JsonBuilder()
json {
    s set
}
println json.toPrettyString()

def possibleValues = []
['',null,' '].groupBy { it?.trim() }.keySet().each {
    possibleValues << it?.trim()
}
possibleValues.removeAll([null, ''])
println possibleValues.size()