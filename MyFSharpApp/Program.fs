open System
open System.IO
open System.Text.RegularExpressions

let sumValidMulResults (input: string) =
    // Define the regular expression pattern for valid mul(X,Y) instructions
    let pattern = @"mul\((\d{1,3}),(\d{1,3})\)"
    let matches = Regex.Matches(input, pattern)
    
    // Sum the products of valid instructions
    matches
    |> Seq.cast<Match>
    |> Seq.sumBy (fun m ->
        let x = int (m.Groups.[1].Value)
        let y = int (m.Groups.[2].Value)
        x * y
    )

let filePath = "./input.txt"

// Read the contents of the file
try
    let memory = File.ReadAllText(filePath)
    let result = sumValidMulResults memory
    printfn "The sum of valid mul results is: %d" result
with
| :? FileNotFoundException -> printfn "Error: File not found at path '%s'" filePath
| ex -> printfn "An error occurred: %s" ex.Message
