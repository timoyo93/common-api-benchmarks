# Standalone

Install the virtual environment and create a new environment to isolate your packages and development from another Python projects.
``` shell
pip install virtualenv
virtualenv <env_name>
```

Running this app on standalone environment
``` shell
source <env_name>/bin/activate
pip install -r requirements.txt
python main.py
```

# Server

Creating and running a docker image.
``` shell
docker build -t python-flask .
docker run -p 5000:5000 python-flask
```