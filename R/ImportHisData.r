# clear all data in working environment
rm(list=ls())
x <- read.csv("~/Desktop/data.csv",header=FALSE, )

summary(x)

x.1 <- x[seq(1,100,10),]
x.2 <- x[seq(2,100,10),]
x.3 <- x[seq(3,100,10),]
x.4 <- x[seq(4,100,10),]
x.5 <- x[seq(5,100,10),]

x.1.ave.pos.numeric <- apply(x.1[,c(3,5,7,9,11)],1,as.numeric)
x.1.ave.pos <- apply(x.1.ave.pos.numeric[,c(3,5,7,9,11)],1, mean)

x.1.ave.neg <- apply(x.1[,c(4,6,8,10,12)],1,mean)

x.2.ave.pos <- apply(x.2[,c(3,5,7,9,11)],1,mean)
x.2.ave.neg <- apply(x.2[,c(4,6,8,10,12)],1,mean)

x.3.ave.pos <- apply(x.3[,c(3,5,7,9,11)],1,mean)
x.3.ave.neg <- apply(x.3[,c(4,6,8,10,12)],1,mean)

x.4.ave.pos <- apply(x.4[,c(3,5,7,9,11)],1,mean)
x.4.ave.neg <- apply(x.4[,c(4,6,8,10,12)],1,mean)

x.5.ave.pos <- apply(x.5[,c(3,5,7,9,11)],1,mean)
x.5.ave.neg <- apply(x.5[,c(4,6,8,10,12)],1,mean)

pdf("temp.pdf")

plot(x.1.ave.pos,lty=1,col=1,type="both",ylim=c(0,1))
lines(x.1.ave.neg,lty=2,col=1,type="both")

lines(x.2.ave.neg,lty=1,col=2,type="both")
lines(x.2.ave.neg,lty=2,col=2,type="both")

lines(x.3.ave.neg,lty=1,col=3,type="both")
lines(x.3.ave.neg,lty=2,col=3,type="both")

lines(x.4.ave.neg,lty=1,col=4,type="both")
lines(x.4.ave.neg,lty=2,col=4,type="both")

lines(x.5.ave.neg,lty=1,col=5,type="both")
lines(x.5.ave.neg,lty=2,col=5,type="both")

dev.off()
