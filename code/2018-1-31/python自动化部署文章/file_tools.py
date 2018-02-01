#! usr/bin/python
# coding=utf-8

import os

import time

def copy_dirs(source_dir, target_dir):
	
	os_name = os.name

	if 'nt' == os_name:
		
		pass

	elif 'posix' == os_name:

		cmd = ('cp -rf %s/. %s' % (source_dir,target_dir))

		os.system(cmd)


def copy_image(source_dir,target_dir):

	checkout_dirs(target_dir)

	subdir = time.strftime('%Y-%m-%d',time.localtime(time.time()))

	checkout_dirs(subdir)

	imgae_dir = ('%s/%s/'%(target_dir,subdir))

	copy_dirs(source_dir,imgae_dir)

	return subdir


def change_image_url(file_name,old,new):

	file_dir = (file_name + '.md')

	print(file_dir)

	file = open(file_dir,'r')

	file_content = file.read()

	file_content_ = file_content.replace(old,new)

	file.close()

	file_ = open(file_dir,'w')

	file_.write(file_content_)

	file_.close()

	return True

def checkout_dirs(target_dir):
	
	has_dir = os.path.exists(target_dir)

	if not has_dir:
		
		os.mkdir(target_dir)
	
	os.chdir(target_dir)

