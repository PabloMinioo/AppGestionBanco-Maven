Vagrant.configure("2") do |config|

  # Nombre de la Imagen utilizada
  config.vm.box = "ubuntu/focal64"

  # Configuración de la máquina virutal
  config.vm.hostname = "Pablo"

  # Redireccionamos los puertos de la máquina virtual y el host.
  # De esta manera, podremos accerder al servicio que se ejecuta en el puerto 80 con localhost:8080 en mi PC
  config.vm.network "forwarded_port", guest: 80, host: 8080

  # Configuramos el provider para virtual box y sus propiedades
  config.vm.provider "virtualbox" do |vb|
    vb.name = "AppGestionBancoMaven"
    vb.memory = "4096"
    vb.cpus = 2
  end

  # Configuramos para que cargue el archivo bootstrap
  config.vm.provision "shell", path: "bootstrap.sh"

end