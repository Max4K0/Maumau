FROM hseeberger/scala-sbt:17.0.2_1.6.2_3.1.1

RUN apt-get update

EXPOSE 8081

WORKDIR /persistence

ADD . /persistence

CMD sbt run
