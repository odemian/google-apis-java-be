# Google apis java backend

## Endpoints

### /gmail
Exposes gmail information to the user

* GET /drafts: get all user draft

### /merge
Merge related stuff

* POST /: send merge

## Issues

### Java API (same for other languages that are not AppsScript) does not have Sheet entity
There is no such think as get sheet and set values (as we do in AppsScript).
Instead, in order to do sheet manipulation, you have to indicate A1 notation range
and spreadsheet id (instead of sheet id you use sheet name).

### Token refresh
The user token passed as header will expire when running long merges.

### User quota
It doesn't seem to be possible to read user quota using Java API
