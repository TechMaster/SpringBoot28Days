          filters:
          - StripPrefix=1
          - name: RequestRateLimiter
            args:
              redis-rate-limiter: 
                replenishRate: 1
                burstCapacity: 2
                requestedTokens: 1