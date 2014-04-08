data <- read.csv("~/Desktop/data.csv", header=FALSE)

# make separate object for each agent
# grab every 10th line
# pool together those lines

# pulls out those rows
x.1 <- data[seq(1,100,10),]

# then plot each row
# average pos/neg and plot those
x.1.ave.pos <- apply(x.1[, c(3,5,7,9,11)], 1, mean)

x.1.ave <- (x.1[,3]+ x.1[,5] + x.1[,7] + x.1[,9] + x.1[,11]) / 5

# x.1.ave.pos

# plot 1-10,

plot(1:10,ylim=c(0,1),col=0)

# plot line 1
# pch = point
# col = color
# type = gives both line and point
lines(1:10,x.1.pos.ave,col=1,pch=1,type="b")

#lty = 1 line type
lines(1:10,x.1.neg.ave,col=1,pch=1,lty=2,type="b")
