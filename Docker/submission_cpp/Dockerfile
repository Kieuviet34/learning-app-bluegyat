FROM gcc:12

RUN apt-get update && apt-get install -y coreutils

RUN useradd -m runner
WORKDIR /home/runner
USER runner

VOLUME ["/home/runner/code"]

ENTRYPOINT ["bash","-lc",\
  "timeout 5s g++ /home/runner/code/*.cpp -O2 -o /tmp/a.out && \
   timeout 2s /tmp/a.out < /home/runner/code/input.txt > /home/runner/output/output.txt"]