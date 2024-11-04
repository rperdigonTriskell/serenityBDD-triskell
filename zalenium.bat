@echo off
docker run --rm -ti --name zalenium -p 4444:4444 ^
    -v /var/run/docker.sock:/var/run/docker.sock ^
    -v C:\Users\your_user_name\temp\videos:/home/seluser/videos ^
    --privileged dosel/zalenium start