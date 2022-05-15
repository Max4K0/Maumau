FROM hseeberger/scala-sbt:17.0.2_1.6.2_3.1.1
RUN rm -rf /var/lib/apt/lists/* && \
    apt-get update && apt-get install -y libxrender1 libxtst6 libxi6 libgl1-mesa-glx libgtk-3-0
EXPOSE 8080
WORKDIR /maumau
ADD . /maumau
RUN sbt compile
CMD sbt run