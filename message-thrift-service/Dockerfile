FROM python:3-alpine
MAINTAINER Yourtion yourtion@gmail.com

EXPOSE 9090
ENV PYTHONPATH /app
WORKDIR /app/message

COPY __init__.py ../
COPY requirements.txt ../
RUN pip install --no-cache-dir -r ../requirements.txt

COPY message ../message

ENTRYPOINT ["python", "./message_service.py"]
