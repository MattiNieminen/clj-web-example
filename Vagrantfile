# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure(2) do |config|
  config.vm.define "mongodb" do |v|
    v.vm.provider "docker" do |d|
      d.image = "mongo:3"
      d.ports = ["27017:27017"]
      d.vagrant_vagrantfile = "./Vagrantfile.proxy"
      d.force_host_vm = true
    end
  end
end
