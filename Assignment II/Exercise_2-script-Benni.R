
#load all files
basicPath <- "C:/Users/Benjamin/Documents/GitHub/BioinformaticsI-Assigments/Assignment II/"
BLOSUM50<- read.table(file=paste0(basicPath,"material/substitution_matrices/BLOSUM50.txt"))
BLOSUM62<- read.table(file=paste0(basicPath,"material/substitution_matrices/BLOSUM62.txt"))
BLOSUM80<- read.table(file=paste0(basicPath,"material/substitution_matrices/BLOSUM80.txt"))
PAM250<- read.table(file=paste0(basicPath,"material/substitution_matrices/PAM250.txt"))
PAMN<- read.table(file=paste0(basicPath,"material/substitution_matrices/PAMN.txt"))

#Now m