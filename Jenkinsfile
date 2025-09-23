---
- name: Setup Docker and deploy Spring Boot microservice
  hosts: localhost
  connection: local
  become: true

  tasks:
    - name: Update apt cache
      apt:
        update_cache: yes

    - name: Remove conflicting containerd.io package
      apt:
        name: containerd.io
        state: absent

    - name: Install Docker using default Ubuntu repo
      apt:
        name: docker.io
        state: present

    - name: Install supporting tools
      apt:
        name:
          - python3-pip
        state: present

    - name: Install docker SDK for python
      pip:
        name: docker

    - name: Ensure Docker service is running
      systemd:
        name: docker
        state: started
        enabled: yes

    - name: Build Docker image for Spring Boot app
      community.docker.docker_image:
        build:
          path: "{{ playbook_dir }}"
        name: financeme-banking
        tag: latest

    - name: Stop old container if exists
      community.docker.docker_container:
        name: financeme-banking
        state: absent
      ignore_errors: true

    - name: Run Docker container for Spring Boot app
      community.docker.docker_container:
        name: financeme-banking
        image: financeme-banking:latest
        state: started
        restart_policy: always
        published_ports:
          - "8081:8081"
