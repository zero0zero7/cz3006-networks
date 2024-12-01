import pandas as pd

df = pd.read_csv('SFlow_Data_lab4.csv', header=None)
df = df.iloc[:, :-1]
df.columns = ['type', 'sflow_agent_address', 'inputPort', 'outputPort', 'src_MAC', 'dst_MAC', 'ethernet_type', 'in_vlan', 'out_vlan', 'src_IP', 'dst_IP', 'IP_protocol', 'ip_tos', 'ip_ttl', 'udp_src_port/tcp_src_port/icmp_type', 'udp_dst_port/tcp_dst_port/icmp_code', 'tcp_flags', 'packet_size', 'IP_packet_size', 'sampling_rate']
df = df[df['type'] != 'CNTR']


# print(df.shape)
# print(df.head(5))

def top_n(column_name, n):
	counts = df.groupby(column_name).size().reset_index(name='count')
	descending_counts = counts.sort_values(by='count', ascending=False)
	return descending_counts.head(n)

# part a
# top_5_talkers
print("\nTop 5 Talkers: ")
print(top_n('src_IP', 5))
# top_5_listeners
print("\nTop 5 Listeners: ")
print(top_n('dst_IP', 5))


# part b
print("\n(B)")
print(top_n('IP_protocol', 20))


# part c
print("\n(C)")
# top 5 destination ports
print("\nTop 5 Destination Ports: ")
print(top_n('udp_dst_port/tcp_dst_port/icmp_code', 5))


# part d
print("\n(D)")
print("\n Total traffic in megabytes: ")
print(df['IP_packet_size'].sum())
print(df['IP_packet_size'].sum() * 2048 / 1000000)


# part e
print("\n(E)")
print("\n Top 5 communication pairs by IP address: ")
top_5_comm_pairs = top_n(['src_IP', 'dst_IP'], 5)
print(top_5_comm_pairs)
print(top_5_comm_pairs['count'].sum(), df.shape)

print(top_n('ethernet_type', 10))
# print(top_n('sampling_rate', 10))
print(top_n('type', 10))



