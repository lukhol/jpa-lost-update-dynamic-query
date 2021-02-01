Hibernate by default create update queries in startup time in order to improve
peformance of generatig queries. We can turned it off using @DynamicUpdate on particular
entity or for all entities in hibernate configuration properties.

Without dynamic updates we can have lost updates on some fields. For eg. if we have
a class with fields `a` and `b` and thread `x` start transaction which takes 1 second
and modify only field `a` and in the mean time there is thread `y` starting transaction which
takes 100ms and modify only field `b` then transction 'x' will override field `b` changed by
'y' transaction.

Solutions: @DynamicUpdates, @Version (optimistic locking), pesimistic locking.

In project there is example models and test running in test contains showing this behavior.a
