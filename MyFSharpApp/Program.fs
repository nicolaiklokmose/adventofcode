open System
open System.IO
open System.Text.RegularExpressions

// Part one
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

// Part two
let sumValidMulResultsConditional (input: string) =
    let mulPattern = @"mul\((\d{1,3}),(\d{1,3})\)"
    let doPattern = @"do\(\)"
    let dontPattern = @"don't\(\)"
    let mutable isEnabled = true
    let mutable total = 0

    // Match all relevant instructions
    let instructions = Regex.Matches(input, $"{mulPattern}|{doPattern}|{dontPattern}")

    // Iterate through each instruction
    for instruction in instructions do
        let value = instruction.Value
        if Regex.IsMatch(value, doPattern) then
            isEnabled <- true
        elif Regex.IsMatch(value, dontPattern) then
            isEnabled <- false
        elif isEnabled && Regex.IsMatch(value, mulPattern) then
            let matchGroups = Regex.Match(value, mulPattern).Groups
            let x = int (matchGroups.[1].Value)
            let y = int (matchGroups.[2].Value)
            total <- total + (x * y)

    total

let filePath = "./input.txt"

// Read the contents of the file and process
try
    let memory = File.ReadAllText(filePath)

    let result1 = sumValidMulResults memory
    printfn "Part 1: The sum of valid mul results is: %d" result1
    
    let result2 = sumValidMulResultsConditional memory
    printfn "Part 2: The sum of enabled mul results is: %d" result2
with
| :? FileNotFoundException -> printfn "Error: File not found at path '%s'" filePath
| ex -> printfn "An error occurred: %s" ex.Message
