FROM hseeberger/scala-sbt:16.0.1_1.5.4_2.13.6
RUN apt-get update && apt-get install -y libxrender1 libxtst6 libxi6 libgl1-mesa-glx
WORKDIR /maumau
ADD . /maumau
CMD sbt run -ti