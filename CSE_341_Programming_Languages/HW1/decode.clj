
; *********************************************
; *  341 Programming Languages                *
; *  Fall 2017                                *
; *  Author: Hakki Erdem Duman                *
; *********************************************

;; utility functions 

(load-file "include.clj") ;; "c2i and "i2c"
(ns clojure.examples.hello
   (:gen-class))
(require '[clojure.string :as str])

(use 'clojure.java.io)
(use '[clojure.string :only [index-of]])

(comment "this function takes a filename as a parameter, reads the document from the given file and returns"
		 "the document."
		 "A Three dimensional list is prepared for the document. First dimension stores a word from"
		 "a line. Second dimension stores the whole line and the third dimension stores the whole file.")

(defn read-as-list [filename]
	(def stringList [])
	(def bigList '())
	(with-open [fileptr (clojure.java.io/reader filename)]
    	(doseq [line (line-seq fileptr)]
    		(do 
    			(def tempList (str/split line #" ")) ;to split the words that is found on a line.
    			(def counter (atom 0))
    			(def paragList [])
    			(while(< @counter (count tempList))
    				(do
    					(def paragList (conj paragList (nth tempList @counter)))
	    				(swap! counter inc)
	    			)
    			)
    			(def stringList (conj stringList paragList))
    		)
      	)
    )
    (def counter (atom 0))

    (while(< @counter (count stringList))
    	(do
    		(def tempList '())
    		(def innerCounter (atom 0))
    		(while(< @innerCounter (count (nth stringList @counter)))
    			(do
    				(def temp (seq (nth(nth stringList @counter)@innerCounter)))
    				(def tempList (conj tempList temp))
    				(swap! innerCounter inc)
    			)
    		)
    		(def tempList (reverse tempList))

    		(def bigList (conj bigList tempList))
    		(swap! counter inc)
    	)
    )
    (def bigList (reverse bigList)) ;the document is here.
    bigList
)

;; -----------------------------------------------------
;; HELPERS
;; *** PLACE YOUR HELPER FUNCTIONS BELOW ***

(comment "this function takes a word and two alphabets (one of them is the normal alphabeth and the other is)"
		 "the solved one) and returns the solved word.")

(defn myDecoder [word mainAlphabeth trueAlphabeth]
	
	(def returnableWord '())
	(def counter (atom 0))

	(while(< @counter (count word))
		(def theIndex (.indexOf mainAlphabeth (nth word @counter)))
		(def returnableWord (conj returnableWord (symbol(str(nth trueAlphabeth theIndex)))))
		(swap! counter inc)
	)
	(def returnableWord (reverse returnableWord))

	returnableWord

)

(comment "this function takes two lists as parameters and combines them.")

(defn conjTheLists [common notCommon]

	(def counter (atom 0))
	(def letterList '())

	(while(< @counter (count common))
		(def letterList (conj letterList (nth common @counter)))
		(swap! counter inc)
	)

	(def counter (atom 0))

	(while(< @counter (count notCommon))
		(def letterList (conj letterList (nth notCommon @counter)))
		(swap! counter inc)
	)

	(def letterList (reverse letterList))

	letterList

)

(comment "this function takes a word as parameter and "
		 "returns true whether the word is inside the given dictionary,"
		 "returns false whether the word isn't inside the given dictionary.")

(defn spell-checker-0 [word]
	
	(def myStr (apply str word))
	(def flag false)
	

	(with-open [fileptr (clojure.java.io/reader "dictionary2.txt")]
    	(doseq [line (line-seq fileptr)]
    		(if(= line myStr)
    			(do
    				(def flag true)
    			
    			)
    		)

    	)
    )


	flag
	
)

(comment "this function does the same thing with spell-checker-0"
		 "but this one's way is different than the other.")

(defn spell-checker-1 [word]
	(def fileList '())
	(def myStr (apply str word))
	(def flag false)

 	(with-open [fileptr (clojure.java.io/reader "dictionary2.txt")]
    	(doseq [line (line-seq fileptr)]
    		(def fileList (conj fileList line))
    	)
    )

 	(if(not= (.indexOf fileList myStr) -1) ;if the word is not exist, function returns -1.
 		(do
 			(def flag true)
 		)
 	)

 	flag

)



;; -----------------------------------------------------
;; DECODE FUNCTIONS

(comment "this function takes a paragraph (2 dimensional list) as parameter and solves the"
		 "alphabeth with a simple way."
		 "returns an inner function that translates the encoded word with using decrypted alphabeth.")

(defn Gen-Decoder-A [paragraph]
	
	(def alphabeth '(\a \b \c \d \e \f \g \h \i \j \k \l \m \n \o \p \q \r \s \t \u \v \w \x \y \z))

	(def shuffledOne (shuffle alphabeth))
	
	(def xcounter(atom 0))
	

	(while (< @xcounter (count paragraph))
		(def ycounter(atom 0))
		(def aWordList '())
		(def aWord (nth paragraph @xcounter))
		(while (< @ycounter (count aWord))
			(def aWordList (conj aWordList (nth shuffledOne(.indexOf alphabeth (nth aWord @ycounter)))))
			(swap! ycounter inc)
		)

		(def aWordList (reverse aWordList))

		
		(def flag (spell-checker-0 aWordList))

		(if (= flag false)
			(do
				(def shuffledOne (shuffle alphabeth))
				(def xcounter (atom 0))
			)
			(swap! xcounter inc)
		)

	)

	(defn innerFunc [word] 
		(myDecoder word alphabeth shuffledOne)
	)

	innerFunc
	
)

(comment "this function does the same thing with Gen-Decoder-A but with a better way."
		 "this one generates an alphabeth with using the information of letter's frequences."
		 "generating the alphabeth with this information makes the program way faster.")

(defn Gen-Decoder-B-0 [paragraph]

	(def commonOnes '(\e \t \a \o \i \n))
	(def notCommonOnes '(\b \c \d \f \g \h \j \k \l \m \p \q \r \s \u \v \w \x \y \z))

	(def commonShuffle (shuffle commonOnes))
	(def notCommonShuffle (shuffle notCommonOnes))

	(def alphabeth (conjTheLists commonOnes notCommonOnes))
	(def shuffledOne (conjTheLists commonShuffle notCommonShuffle))

	(def xcounter(atom 0))
	

	(while (< @xcounter (count paragraph))
		(def ycounter(atom 0))
		(def aWordList '())
		(def aWord (nth paragraph @xcounter))
		(while (< @ycounter (count aWord))
			(def aWordList (conj aWordList (nth shuffledOne(.indexOf alphabeth (nth aWord @ycounter)))))
			(swap! ycounter inc)
		)

		(def aWordList (reverse aWordList))

		(def flag (spell-checker-0 aWordList))

		(if (= flag false)
			(do
				(def shuffledOne (shuffle alphabeth))
				(def xcounter (atom 0))
			)
			(swap! xcounter inc)
		)

	)


	(defn innerFunc [word] 
		(myDecoder word alphabeth shuffledOne)
	)

	innerFunc

)

(defn Gen-Decoder-B-1 
	[paragraph]
  	;you should implement this function
)

(comment "this function takes document and a decoder function as parameters and returns the decoded document.")

(defn Code-Breaker [document decoder]
  	(def funcPtr (decoder (nth document 0)))
  	(def fcounter (atom 0))
  	(def documentList '())

  	(while(< @fcounter (count document))
  		(def paragraphList '())
  		(def scounter (atom 0))
  		(def aParagraph (nth document @fcounter))
  		(while (< @scounter (count aParagraph))
  			(def justAWord (nth aParagraph @scounter))
  			(def returnedWord (funcPtr justAWord))
  			(def paragraphList (conj paragraphList returnedWord))
  			(swap! scounter inc)
  		)

  		(def paragraphList (reverse paragraphList))
  		(def documentList (conj documentList paragraphList))
  		(swap! fcounter inc)

  	)
  	(def documentList (reverse documentList))

  	documentList
)

;; -----------------------------------------------------
;; Test code...

(defn test_on_test_data
	[]
	(let [doc (read-as-list "document1.txt")]
		(println doc)
		(println (Code-Breaker doc Gen-Decoder-A))
	)

	
)


;; test code...
(test_on_test_data)



 
