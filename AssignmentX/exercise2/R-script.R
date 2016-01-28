wd<-"C:/Users/benja/Desktop/material"
setwd(wd)
library(plotrix)
x<-t(t(seq(1, 23)))

#velvet coverage
velvet7results <- read.delim(c(wd,"/velvet/velvet_result_7/stats.txt"), header =TRUE)
velvet15results <- read.delim("C:/Users/benja/Desktop/material/velvet/velvet_result_15/stats.txt", header = TRUE)
velvet23results <- read.delim("C:/Users/benja/Desktop/material/velvet/velvet_result_23/stats.txt", header = TRUE)
velvet31results <- read.delim("C:/Users/benja/Desktop/material/velvet/velvet_result_31/stats.txt", header = TRUE)


#Soap k-mer Freq => cov
Soap35mer<- read.table("C:/Users/benja/Documents/Git/BioinformaticsI-Assigments/AssignmentX/exercise2/35soap-cov.csv", quote="\"", comment.char="")
Soap43mer<- read.table("C:/Users/benja/Documents/Git/BioinformaticsI-Assigments/AssignmentX/exercise2/43soap-cov.csv", quote="\"", comment.char="")
Soap45mer<- read.table("C:/Users/benja/Documents/Git/BioinformaticsI-Assigments/AssignmentX/exercise2/45soap-cov.csv", quote="\"", comment.char="")
Soap47mer<- read.table("C:/Users/benja/Documents/Git/BioinformaticsI-Assigments/AssignmentX/exercise2/47soap-cov.csv", quote="\"", comment.char="")


#pictures SOAP


#pictures Velvet  
png("velvet7kmersCoverage.png")
velvet7Hist <- hist(velvet7results$short1_cov, xlim=range(0,120), breaks=1000, main="coverage for 7 k-mers")
dev.off()

png("velvet7kmersWheightedCoverage.png")
velvet7HistWheight <- weighted.hist(velvet7results$short1_cov, velvet7results$lgth, breaks=0:50, main="coverage for wheighted 7 k-mers")
dev.off()

png("velvet15kmersCoverage.png")
velvet15Hist <- hist(velvet15results$short1_cov, xlim=range(0,70), breaks=1000, main="coverage for 15 k-mers")
dev.off()

png("velvet15kmersWeightedCoverage.png")
velvet15HistWheight <- weighted.hist(velvet15results$short1_cov, velvet15results$lgth, breaks=0:50, main="coverage for wheighted 15 k-mers")
dev.off()

png("velvet23kmersCoverage.png")
velvet23Hist <- hist(velvet23results$long_cov, xlim=range(0,80), breaks=1000, main="coverage for 21 k-mers")
dev.off()

png("velvet23kmersWeightedCoverage.png")
velvet23HistWheight <- weighted.hist(velvet23results$short1_cov, velvet23results$lgth, breaks=0:50, main="coverage for wheighted 21 k-mers")
dev.off()

png("velvet31kmersCoverage.png")
velvet31Hist <- hist(velvet31results$long_cov, xlim=range(0,80), breaks=1000, main="coverage for 31 k-mers")
dev.off()

png("velvet31kmersWeightedCoverage.png")
velvet31HistWheight <- weighted.hist(velvet31results$short1_cov, velvet31results$lgth, breaks=0:50, main="coverage for wheighted 31 k-mers")
dev.off()


#pictures SOAP

png("Soap35merCoverage.png")
hist(as.double(Soap35mer$V1), ylim=range(0,100), breaks=145, main="coverage for 35 k-mers")
dev.off()

png("Soap43merCoverage.png")
hist(as.double(Soap43mer$V1), ylim=range(0,100), breaks=8, main="coverage for 43 k-mers")
dev.off()

png("Soap45merCoverage.png")
hist(as.double(Soap45mer$V1), ylim=range(0,100), breaks=8, main="coverage for 45 k-mers")
dev.off()

png("Soap47merCoverage.png")
 hist(as.double(Soap47mer$V1), ylim=range(0,100), breaks=8, main="coverage for 47 k-mers")
dev.off()
