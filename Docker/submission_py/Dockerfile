
FROM python:3.10-slim

RUN apt-get update && apt-get install -y coreutils

RUN useradd -m runner
WORKDIR /home/runner
USER runner

VOLUME ["/home/runner/code"]

ENTRYPOINT ["bash","-lc", "\
  timeout --signal=SIGKILL 2s python3 code/main.py  < /home/runner/code/input.txt > /home/runner/output/output.txt"]
