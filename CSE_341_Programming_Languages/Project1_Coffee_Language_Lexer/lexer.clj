;	Hakkı Erdem Duman
;	    151044005

;READ PLEASE.
(comment "This project has an interpreter system. If you enter the command of 'coffee', it will"
		 "open an interpreter for you. You can make your processes line by line."
		 "If you enter 'coffee filename', it will make the process of the entire file and"
		 "open you an interpreter again."
		 "You can type 'quit' to quit from the interpreter.")

(ns clojure.examples.hello
   (:gen-class))
(require '[clojure.string :as str]) 

(use 'clojure.java.io)
(use '[clojure.string :only [index-of]])


(comment "This function gets a parsed line as parameter and catagorize it."
		 "Returns the analyzed vector."
		 "When only two catagories remain (IntegerValue and ID), function"
		 "tries to convert the string to the integer. If the conversion"
		 "is done succesfully, that means the given string is a number."
		 "If the conversion is failed, function throws and exception and"
		 "in the catch block, it is identified as ID."
)

(defn list_of_operators [paramOp]

	(def keywords ["and" "or" "not" "equal" "append" "concat" 
				   "set" "deffun" "for" "while" "if" "then" 
				   "else" "defvar"])

	(def operators ["+" "-" "*" "/" "(" ")"])

	(def binaryValues ["true" "false"])

	(def integerValues [])


	(def lexerVec [])

	(print "\n")

	(doseq [each paramOp]
		
		(if(not= (.indexOf keywords each) -1)
			(do
				(def lexerVec (conj lexerVec (str each " : keyword\n")))
			)
			(do
				(if(not= (.indexOf operators each) -1)
					(do
						(def lexerVec (conj lexerVec (str each" : operator\n")))
					)
					(do
						(if(not= (.indexOf binaryValues each) -1)
							(do
								(def lexerVec (conj lexerVec (str each " : BinaryValue\n")))
							)
							(do
								(try
									(Integer/parseInt each)
									(def lexerVec (conj lexerVec (str each " : IntegerValue\n")))
									(catch Exception e (def lexerVec (conj lexerVec (str each " : ID\n"))))
								)
							)

						)
					)
				)	
			)
		)

	)

	(println lexerVec)
	lexerVec

)

(comment "this function gets a filename as parameter and does the same"
		 "job for given file."
		 "returns a vector of all analyzed lines.")

(defn lexer [filename]

	(def returnList '())

	(with-open [fileptr (clojure.java.io/reader filename)]
    	(doseq [line (line-seq fileptr)]
    		(def parsedVecInFile (re-seq #"[(+)/*-]|[0-9]+|[a-zA-Z]+" line))
			(def returnList (conj returnList (list_of_operators parsedVecInFile)))
    	)
    )

	(reverse returnList)
    returnList
)

(comment "This is the interpreter function.")

(defn interpreter[]
	(def process_param "something")
	(def a 1)
	(while(= a 1)
		(do
			(print "> ")(flush)
			(def process_param (read-line))
			(if(= process_param "quit")
				(do
					(def a 2)
				)
				(do
					(def parsedVec (re-seq #"[(+)/*-]|[0-9]+|[a-zA-Z]+" process_param))
					(list_of_operators parsedVec)
				)
				
			)
			
		)
	)
)


(comment "This is the main function which gets the starting command of the program.")

(defn main[]

	(print "$ ")(flush)
	(def process (read-line))
	(def processVec (str/split process #" "))

	(if (and (= (count processVec) 1) (= (nth processVec 0) "coffee"))
		(do
			(interpreter)
		)
	)

	(if (and (= (count processVec) 2) (= (nth processVec 0) "coffee"))
		(do
			(lexer (nth processVec 1))
			(interpreter)
		)
	)
)

(main)
