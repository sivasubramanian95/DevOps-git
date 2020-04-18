#build stage
FROM golang:1.8.3 AS builder
MAINTAINER HaeyoonJo

ENV VAULT_VERSION=0.7.3

## clone source code
WORKDIR /go/src/github.com/hasicorp
RUN git clone https://github.com/hashicorp/vault.git

# build vault
WORKDIR /go/src/github.com/hashicorp/vault
RUN git checkout v"${VAULT_VERSION}"
RUN make bootstrap
RUN make dev

# final stage
FROM debian:jessie

COPY --from=builder /go/src/github.com/hashicorp/vault/bin/vault /bin/

CMD ["vault", "server", "-dev"]
