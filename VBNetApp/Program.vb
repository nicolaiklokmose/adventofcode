Imports System
Imports System.Collections.Generic

Module MainModule
    Sub Main()
        Dim MainListOfFaceInformation As New List(Of List(Of String)) From {
            New List(Of String) From {"10", "value1"},
            New List(Of String) From {"2", "value2"},
            New List(Of String) From {"3", "value3"},
            New List(Of String) From {"30", "value4"},
            New List(Of String) From {"51", "value5"},
            New List(Of String) From {"5", "value6"}
        }

        Dim map As New SortedDictionary(Of Integer, List(Of String))()
        For Each sublist As List(Of String) In MainListOfFaceInformation
            Dim key As Integer = Integer.Parse(sublist(0))
            map.Add(key, sublist)
        Next

        Dim sortedList As New List(Of List(Of String))(map.Values)

        Console.WriteLine("Sublists i sortered rækkefølge:")
        For Each sublist As List(Of String) In sortedList
            Console.WriteLine("[" & String.Join(", ", sublist) & "]")
        Next

        MainListOfFaceInformation.Add(New List(Of String) From {"1", "value7"})
        Dim newEntry As List(Of String) = MainListOfFaceInformation.Last()
        map.Add(Integer.Parse(newEntry(0)), newEntry)

        sortedList = New List(Of List(Of String))(map.Values)

        Console.WriteLine("Sublists i sortered rækkefølge efter at have tilføjet ny sublist til mainlist.")
        For Each sublist As List(Of String) In sortedList
            Console.WriteLine("[" & String.Join(", ", sublist) & "]")
        Next
    End Sub
End Module
