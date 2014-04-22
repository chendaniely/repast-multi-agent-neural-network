#require(dplyr)
require(plyr)

# clear all data in working environment
rm(list=ls())
df <- read.csv("~/Desktop/allzeroWithBiasDecay.csv",
              header=FALSE, stringsAsFactors=FALSE)
num.pu.per.bank = 5

summary(df)

for (i in 3:12) {
  df[ ,i] <- as.numeric(df[ ,i])
}
summary(df)

# df <- transform(df,  V3 = as.numeric(V3))
# df <- transform(df,  V4 = as.numeric(V4))
# df <- transform(df,  V5 = as.numeric(V5))
# df <- transform(df,  V6 = as.numeric(V6))
# df <- transform(df,  V7 = as.numeric(V7))
# df <- transform(df,  V8 = as.numeric(V8))
# df <- transform(df,  V9 = as.numeric(V9))
# df <- transform(df,  V10 = as.numeric(V10))
# df <- transform(df,  V11 = as.numeric(V11))
# df <- transform(df,  V12 = as.numeric(V12))
# summary(df)

for (i in num.pu.per.bank){
  nameToUse <- paste("x", i, sep = "")


}

x.1 <- x[seq(1,100,10),]
x.2 <- x[seq(2,100,10),]
x.3 <- x[seq(3,100,10),]
x.4 <- x[seq(4,100,10),]
x.5 <- x[seq(5,100,10),]

x.1.ave.pos <- apply(x.1[,c(3,4,5,6,7)],1, mean)
x.1.ave.neg <- apply(x.1[,c(8,9,10,11,12)],1,mean)

x.2.ave.pos <- apply(x.2[,c(3,4,5,6,7)],1,mean)
x.2.ave.neg <- apply(x.2[,c(8,9,10,11,12)],1,mean)

x.3.ave.pos <- apply(x.3[,c(3,4,5,6,7)],1,mean)
x.3.ave.neg <- apply(x.3[,c(8,9,10,11,12)],1,mean)

x.4.ave.pos <- apply(x.4[,c(3,4,5,6,7)],1,mean)
x.4.ave.neg <- apply(x.4[,c(8,9,10,11,12)],1,mean)

x.5.ave.pos <- apply(x.5[,c(3,4,5,6,7)],1,mean)
x.5.ave.neg <- apply(x.5[,c(8,9,10,11,12)],1,mean)

#pdf("temp2.pdf")

plot(x.1.ave.pos,lty=1,col=1,type="both",ylim=c(0,1))
lines(x.1.ave.neg, lty=2, col=1, type="both", lwd=3)

lines(x.2.ave.neg,ylim=c(0,1),lty=1,col=2,type="both")
lines(x.2.ave.neg,lty=2,col=2,type="both", lwd=3)

lines(x.3.ave.neg,lty=1,col=3,type="both")
lines(x.3.ave.neg,lty=2,col=3,type="both", lwd=3)

lines(x.4.ave.neg,lty=1,col=4,type="both")
lines(x.4.ave.neg,lty=2,col=4,type="both", lwd=3)

lines(x.5.ave.neg,lty=1,col=5,type="both")
lines(x.5.ave.neg,lty=2,col=5,type="both", lwd=3)

#dev.off()

