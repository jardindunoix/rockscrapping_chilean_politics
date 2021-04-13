package com.example.rockscrappinchileanpolitics.d_utilities.top_functions

 fun isAllCapsUp(element:String?):Boolean {
	val regex = """[abcdefghijklmnñopqrstuvwxyz]""".toRegex()
	return regex.containsMatchIn(input = element.toString()).not()
}

 fun firstLetter(word:String):Int {
	val letters = word.trim().split("")
	val letterOne = letters[1]
	return letterToNumber(letterOne)
}

 fun letterToNumber(letter:String):Int {
	val oldValue = " "
	val newValue = ""
	val listChar = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ".replace(oldValue, newValue).split("")
	for ((i, char) in listChar.withIndex()) {
		when (char == letter.trim()) {
			true -> return i
		}
	}
	return 0
}
