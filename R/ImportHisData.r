# clear all data in working environment
rm(list=ls())
x <- read.csv("~/Desktop/allzeroWithBias.csv", header=FALSE, stringsAsFactors=FALSE)

summary(x)
x<- transform(x,  V3 = as.numeric(V3))
x<- transform(x,  V4 = as.numeric(V4))
x<- transform(x,  V5 = as.numeric(V5))
x<- transform(x,  V6 = as.numeric(V6))
x<- transform(x,  V7 = as.numeric(V7))
x<- transform(x,  V8 = as.numeric(V8))
x<- transform(x,  V9 = as.numeric(V9))
x<- transform(x,  V10 = as.numeric(V10))
x<- transform(x,  V11 = as.numeric(V11))
x<- transform(x,  V12 = as.numeric(V12))
summary(x)

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

