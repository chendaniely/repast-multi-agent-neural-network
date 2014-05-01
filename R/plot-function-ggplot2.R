require(ggplot2)
require(scales)
data <- "/home/dchen/git/repast-neural-network-agent-based-model/R/data/sanity10-5.csv"
numTimeTicks <- 10
numAgents <- 10
x <- read.csv(data, header=FALSE, stringsAsFactors=FALSE)

# Rename dataframe column names
names(x) <- c("time", "agent",
              "p1", "p2", "p3", "p4", "p5",
              "n1", "n2", "n3", "n4", "n5")

# Convert string factors into numerics
for (i in 3:12) {
  x[ ,i] <- as.numeric(x[ ,i])
}

x["pos.avg"] <- apply(x[, c(3,4,5,6,7)], 1, mean)
x["neg.avg"] <- apply(x[, c(8,9,10,11,12)], 1, mean)

rm(g)
limit = "yes"
g <- ggplot(x, aes(x = time))
g <- g + geom_line(aes(y = pos.avg, color = factor(agent), group = agent))
g <- g + geom_line(aes(y = neg.avg, color = factor(agent), group = agent), linetype="dashed")
g <- g + scale_color_discrete(name = "Agent")
#g <- g + scale_y_continuous(labels = comma)
g <- g + scale_x_continuous(breaks=c(0:10))
if (limit == "yes") {
  g <- g + scale_x_continuous(limits=c(0, 5))
}
g <- g + labs(title = "Average Valence Bank Activation Values Over Time", x = "Time Tick", y = "Average Valence Bank Activation Value")
g <- g + facet_wrap(~agent)
g

