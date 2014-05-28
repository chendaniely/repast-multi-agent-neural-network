plot.thesis.data <- function(data, numAgents, numTimeTicks, facet = 'no', limit.x = c('no', 0, 100)) {
  # Takes a CSV or R dataframe from my thesis,
  # assign's column names,
  # converts processing unit values into numerics
  # plots the average activation value for each processing unit for each agent in each time tick
  #
  # Args:
  #   data: String value of the directory and file of CSV
  #   numAgents: Number of agents ran in the simulation of the CSV
  #   numTimeTicks: Number of time ticks ran in the simulation of the CSV
  #   subx: defaults to 0, used to section regions of the x axis,
  #           0 for no sub-sectioning, 1 for yes sub-sectioning
  #   xmin: defaults to 0, the lower bound of the x-axis if subx is 1
  #   xmax: defaults to 0, the upper bound of the x-axis if subx is 1
  #
  # Returns:
  #   Line graph

  # Load data
  require(ggplot2)
  require(scales)

  # if data is a dataframe, just move on with the rest of the function,
  # otherwise it is a csv file and convert the csv to df first
  if (is.data.frame(data) == FALSE){
    x <- read.csv(data, header=FALSE, stringsAsFactors=FALSE)
  } else {
    x <- data
  }

  # Rename dataframe column names
  names(x) <- c("time", "agent",
                "p1", "p2", "p3", "p4", "p5",
                "n1", "n2", "n3", "n4", "n5")

  # Convert string factors into numerics
  for (i in 3:12) {
    x[ ,i] <- as.numeric(x[ ,i])
  }

  x["pos.avg"] <- apply(x[, c(3:7)], 1, mean)

  x["neg.avg"] <- apply(x[, c(8:12)], 1, mean)

  # rm(g)
  g <- ggplot(x, aes(x = time))
  g <- g + geom_line(aes(y = pos.avg, color = factor(agent), group = agent))
  g <- g + geom_line(aes(y = neg.avg, color = factor(agent), group = agent), linetype="dashed")
  if (numAgents <= 10) {
    g <- g + scale_color_discrete(name = "Agent", guide=TRUE)
  } else{
    g <- g + scale_color_discrete(name = "Agent", guide=FALSE)
  }
  #g <- g + scale_y_continuous(labels = comma)
  g <- g + scale_x_continuous(breaks=c(0:10))
  if (limit.x[1] == "yes") {
    g <- g + scale_x_continuous(limits=c(as.numeric(limit.x[2]), as.numeric(limit.x[3])))
  }
  g <- g + labs(title = "Average Valence Bank Activation Values Over Time", x = "Time Tick", y = "Average Valence Bank Activation Value")
  if (facet == 'yes') {
    g <- g + facet_wrap(~ agent)
  }
  g
}

plot.multiple.run.data <- function(data, numAgents, numTimeTicks, facet = 'no', limit.x = c('no', 0, 100))
{
  x <- read.csv(data, header=FALSE, stringsAsFactors=FALSE)
  j <- 1
  gg.list <- list()
  num.rows.per.run <- numTimeTicks * numAgents

  for (i in 1:(nrow(x) / num.rows.per.run))
  {
    print(i)
    print(j)
    data.slice <- x[j:(i * num.rows.per.run),]
    my.plot <- plot.thesis.data(data.slice, numAgents, numTimeTicks, facet, limit.x)
    print(my.plot)
    j <- j + num.rows.per.run
    gg.list <- c(gg.list, my.plot)
  }
  # return(gg.list)
}
