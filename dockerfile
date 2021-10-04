FROM ubuntu
WORKDIR /usr/local/

RUN apt-get update && \
    DEBIAN_FRONTEND=noninteractive apt-get install -y default-jdk maven wget gcc make git libtool libicu-dev libpcre2-dev libasound2-dev automake pkg-config

RUN git clone https://github.com/MycroftAI/mimic1.git
WORKDIR /usr/local/mimic1

RUN ./dependencies.sh --prefix="/usr/local" &&\
    ./autogen.sh &&\
    ./configure --prefix='/usr/local' &&\
    make && \
    make check

RUN mv mimic /usr/local/bin

RUN mkdir /app
WORKDIR /app
COPY . /app
RUN mvn clean install

CMD java -jar ./target/WebTTS-0.0.1-SNAPSHOT.jar