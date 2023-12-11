## Minikube Kubernetes Commands

## start cluster
minikube start 

## Get cluster Info
kubectl get info

# Get information about the nodes
kubectl get nodes

# create a POD using nginx
kubectl run nginx-app --image=nginx:latest

# Get Information about the pods in the cluster
kubctl get pods

# expose the service as NodePort
kubectl expose pod nginx-app --type=NodePort --port=8080 --target-port=80

# Get information about the services in the cluster
kubctl get svc

## Access the service with local browser

minikube service nginx-app

## the above command will create a Tunnel and Open the default broser to access the service



 