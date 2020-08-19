1. Build images in each folder (eatapp and db)
2. tag the images and push them to dockerhub
docker tag eatapp carlosv5/tfm-app:v0
docker push carlosv5/tfm-app:v0
docker tag my-mysql carlosv5/tfm-db:v0
docker push carlosv5/tfm-db:v0
3. Install helm chart
helm install eatapp/ --name eatapp
