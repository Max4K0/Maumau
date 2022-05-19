FROM hseeberger/scala-sbt:17.0.2_1.6.2_3.1.1

RUN apt-get update && apt-get install -y libxrender1 libxtst6 libxi6 libgl1-mesa-glx libgtk-3-0

EXPOSE 8080

WORKDIR /maumau

ADD . /maumau

RUN chmod +x /maumau/run.sh

CMD /bin/bash -c '/maumau/run.sh; /bin/bash'