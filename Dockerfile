FROM hseeberger/scala-sbt
WORKDIR /maumau
ADD . /maumau
CMD sbt test