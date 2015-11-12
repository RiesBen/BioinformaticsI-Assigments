import numpy as np
import matplotlib.pyplot as plt

x = np.arange(0.,8.)

y1 = 12-2*x
y2 = 7-x
y3 = (16-2*x)/3
y4 = (6+x)/2

plt.ylim(0,14)
plt.plot(x,y1,'r', x,y2,'g', x,y3,'b', x,y4,'y')
plt.fill_between(x,y4, where=(x <=2), color='grey', alpha='1')
plt.fill_between(x,y3, where=((x <= 5) & (x>=2)), color='grey', alpha='1')
plt.fill_between(x,y1, where=((x <= 6) & (x>=5)),color='grey', alpha='1')

plt.show()
