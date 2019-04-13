# Create AWS service with AWS Cli
Date : 2018.11.15 21:20 KST

writer : [Haeyoon Jo](https://www.linkedin.com/in/haeyoon-jo-780581b9/)

Reason : [Just for Fun!](https://en.wikipedia.org/w/index.php?title=Just_for_Fun_(book)&redirect=no) `Torvalds's book`
## Introduction

How to use AWS Cli and create AWS services like VPC, Subnet, IGW, Security Group for public EC2 instance. I referred [Amazon Documentation](https://docs.aws.amazon.com/vpc/latest/userguide/vpc-subnets-commands-example.html).

Also, will link to amazon docs if anything for expediency in my explanation in README.md

Follow the contents below : 

### Contents

* preparations for setting up to create AWS services
	- Create AWS account
	- Need Linux/OS X terminal to use AWS cli, and Windows? [refer Windows user to install and use aws cli](https://docs.aws.amazon.com/cli/latest/userguide/awscli-install-windows.html)
	- Create Access Keys ( ID and Secret Access Key ) to requests AWS CLI or AWS API operations.
* Create VPC
* Create Subnet ( public / private )
* Create Internet Gateway ( IGW )
* Create Route table
* Associate Route table with public subnet
* Create Key pair to enter EC2 instance
* Create and add role by Security Group
* Create EC2 instance in public subnet
* Check EC2 instance run and use Linux commands

### Let's START!
#### 1. AWS account

As we knew, we can create AWS Free tier! >> [Create account](https://aws.amazon.com/free/?nc1=h_ls)
#### 2. Install AWS CLI( AWS command Line Interface )
Check pip, python version on your shell and type the command `sudo apt-get install pip`.

```
root@ip-172-31-11-091:~$ pip --version
-bash: pip: command not found

root@ip-172-31-11-091:~$ python --version
Python 2.7.5
```
No pip installed, so try as below.
```
root@ip-172-31-11-091:~$ sudo apt-get install pip

root@ip-172-31-11-091:~$ ls -a ~
.  ..  .aws  .bash_history  .bash_logout  .bashrc  .cache  .gnupg  .local  .profile  .ssh  .viminfo

$ export PATH=~/.local/bin:$PATH

$ source ~/.profile
```
Find your shell's profile script in your user folder. If you are not sure which shell you have, run echo $SHELL.
```
$ ls -a ~
.  ..  .bash_logout  .bash_profile  .bashrc  Desktop  Documents  Downloads
```
Bash – .bash_profile, .profile, or .bash_login.

Zsh – .zshrc

Tcsh – .tcshrc, .cshrc or .login.

So.. check pip version and install AWS CLI
```
root@ip-172-31-11-091:~$ pip --version
pip 9.0.1 from /usr/lib/python2.7/dist-packages (python 2.7)

root@ip-172-31-11-091:~$ pip install awscli --upgrade --user
...

root@ip-172-31-11-091:~$aws --version
aws-cli/1.16.55 Python/2.7.15rc1 Linux/4.15.0-1021-aws botocore/1.12.45
```
And you have to enter access key. See the [documentation](https://docs.aws.amazon.com/general/latest/gr/managing-aws-access-keys.html)

After create it, you can download as .xlsx file to your computer.

Looks like 
```
Access key ID : 3DJK71y******
Secret access key : 1s5APq21KjD*****************
Console login link : https://user123.aws.amazon.com/console
```
Now, we need to confirm your access key by aws configure command.
```
root@ip-172-31-11-091:~$ aws configure
AWS Access Key ID [****************OKHA]: 
AWS Secret Access Key [****************c9y6]: 
Default region name [ap-northeast-2]: 
Default output format [json]: 
```
**ARE YOU DONE?**

We are ready to start to create your lovely AWS service with AWS CLI.

Let's move on next!

#### 3. Create VPC

Here we are.

As I said at the first that referred [Amazon Documentation](https://docs.aws.amazon.com/vpc/latest/userguide/vpc-subnets-commands-example.html) you can see the command and detail.

* creation VPC

```
root@ip-172-31-11-091:~$ aws ec2 create-vpc --cidr-block 10.0.0.0/16
{
    "Vpc": {
        "VpcId": "vpc-022211b2472edf800", 
        "InstanceTenancy": "default", 
        "Tags": [], 
        "CidrBlockAssociationSet": [
            {
                "AssociationId": "vpc-cidr-assoc-07aa94e9463c10ed3", 
                "CidrBlock": "10.0.0.0/16", 
                "CidrBlockState": {
                    "State": "associated"
                }
            }
        ], 
        "Ipv6CidrBlockAssociationSet": [], 
        "State": "pending", 
        "DhcpOptionsId": "dopt-822f76eb", 
        "CidrBlock": "10.0.0.0/16", 
        "IsDefault": false
    }
}
```