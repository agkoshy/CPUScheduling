package cpuscheduling;

import javax.swing.JOptionPane;


public class Operation {

	int n;
	int Burst[] =new int[100];
	float time,average,w;
	float A[]=new float[100];
	float Wt[]=new float[100];

	void getData()
	{
		int i;
		n=Integer.parseInt(JOptionPane.showInputDialog("Enter Number of Processes:"));
		for(i=1;i<=n;i++)
		{
			Burst[i]=Integer.parseInt(JOptionPane.showInputDialog("Enter The BurstTime for Process p"+i+""));
		}
	}

	
	void RoundRobin()
	{

		int i,j,quantum,k;
		int B[]=new int[10];
		int robin[][]=new int[10][10];
		int count[]=new int[10];
		int max=0;
		int m;
		time=0;
		n=Integer.parseInt(JOptionPane.showInputDialog("Enter Number of Processes:"));
		for(i=1;i<=n;i++)
		{
			B[i]=Integer.parseInt(JOptionPane.showInputDialog("Enter The BurstTime for Process p"+i+""));
		}
		quantum=Integer.parseInt(JOptionPane.showInputDialog("Enter The Time Quantum:"));
		m=max/quantum+1;

		//initializing Rrobin array
		for(i=1;i<=n;i++)
		{
			for(j=1;j<=m;j++)
			{
				robin[i][j]=0;
			}
		}
		//placing value in the Rrobin array
		i=1;
		while(i<=n)
		{
			j=1;
			while(B[i]>0)
			{
				if(B[i]>=quantum)
				{
					B[i]=B[i]-quantum;
					robin[i][j]=quantum;
					j++;
				}
				else
				{
					robin[i][j]=B[i];
					B[i]=0;
					j++;
				}
			}
			count[i]=j-1;
			i++;
		}

		//calculating weighting time
		int x=1;
		i=1;
		while(x<=n)
		{
			for(int a=1;a<x;a++)
			{
				Wt[x]=Wt[x]+robin[a][i];
			}
			i=1;
			int z=x;
			j=count[z];
			k=1;
			while(k<=j-1)
			{
				if(i==n+1)
				{
					i=1;
					k++;
				}
				else
				{
					if(i!=z)
					{
						Wt[z]=Wt[z]+robin[i][k];
					}
					i++;
				}
			}
			x++;
		}
		for(i=1;i<=n;i++)
		{
			System.out.println("Waiting Time for process p"+i+":"+Wt[i]);
		}
		//calculating Average Weighting Time
		for(i=1;i<=n;i++)
		{
			time=time+Wt[i];
			average=time/n;
		}
		System.out.print("Total Waiting Time :"+time);
		System.out.print("Average Waiting Time :"+average);

	}


}